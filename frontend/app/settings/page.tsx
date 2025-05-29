import { ModeToggle } from "@/components/theme";
import { GetUser } from "./_action";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import UpdateForm from "@/components/updateForm";

export default async function page() {

    const user = await GetUser();

    return (
        <div className="flex flex-col min-h-screen bg-white dark:bg-black">
            {/* Topbar inside content area (excluding sidebar) */}
            <div className="flex justify-end mr-3 pb-2 border-b border-gray-200 dark:border-gray-800">
                <ModeToggle />
            </div>

            {/* Main grid content, placed below toggle */}
            <main className="flex-1 grid grid-cols-1 md:grid-cols-[35%_65%] min-h-[300px]">
                <div className="justify-center p-6">
                    <Card className="bg-white dark:bg-sky-600">
                        <CardHeader>
                            <CardTitle className="text-center text-xl">Hello! 👋</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <p className="text-xl">Welcome {user.fullName}.</p>
                        </CardContent>
                    </Card>
                </div>
                <div>
                    <Card className="max-w-xl mx-auto w-full shadow-xl mt-2 bg-white dark:bg-black">
                        <CardHeader>
                            <CardTitle className="text-2xl font-semibold">Account Settings</CardTitle>
                        </CardHeader>

                        <CardContent>
                            <UpdateForm user={user} />
                        </CardContent>
                    </Card>
                </div>
            </main>
        </div>
    )
}