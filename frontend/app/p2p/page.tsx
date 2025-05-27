import { ModeToggle } from "@/components/theme";
import { Plus } from 'lucide-react';
import { Card, CardContent } from "@/components/ui/card"
import { Tooltip, TooltipContent, TooltipTrigger } from "@/components/ui/tooltip";
import { Dialog, DialogContent, DialogTrigger } from "@/components/ui/dialog";
import P2pModal from "@/components/p2p-modal";

export default function Page() {

    return (
        <div className="flex flex-col min-h-screen bg-white dark:bg-black">
            {/* Topbar inside content area (excluding sidebar) */}
            <div className="flex flex-row items-center justify-between border-b pb-2 border-gray-200 dark:border-gray-800">
                <Dialog>
                    <DialogTrigger>
                        <Tooltip>
                            <TooltipTrigger asChild>
                                <div className="ml-3 cursor-pointer">
                                    <Plus size={35} />
                                </div>
                            </TooltipTrigger>
                            <TooltipContent>
                                <p>New P2P</p>
                            </TooltipContent>
                        </Tooltip>
                    </DialogTrigger>
                    <DialogContent>
                        <P2pModal />
                    </DialogContent>
                </Dialog>
                <div className="mr-3">
                    <ModeToggle />
                </div>
            </div>


            {/* Main grid content, placed below toggle */}
            <main className="flex-1 grid grid-cols-1 md:grid-cols-[35%_65%] min-h-[300px]">
                <div className="justify-center p-6"></div>
                <div className="items-center justify-center p-6">
                    <h1 className="text-center text-lg font-serif">Transactions</h1>
                    <Card className="bg-white dark:bg-sky-600">
                        <CardContent>

                        </CardContent>
                    </Card>
                </div>
            </main>
        </div>
    )
}
